import { useEffect, useState } from "react";
import axios from "axios";
import ProjectList from "./ProjectList.jsx";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;

export default function CreateProjectForm() {
  const [name, setName] = useState("");
  const [description, setDescription] = useState("");
  const [taskId, setTaskId] = useState([]); // now an array
  const [startDate, setStartDate] = useState("");
  const [endDate, setEndDate] = useState("");
  const [managerId, setManagerId] = useState(""); // optional
  const [tasks, setTasks] = useState([]);

  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState("");

  // Fetch tasks for dropdown
  useEffect(() => {
    axios
      .get(`${BASE_URL}/tasks`)
      .then((res) => setTasks(res.data))
      .catch(() => setErrors({ api: "Failed to load tasks" }));
  }, []);

  const validate = () => {
    const errs = {};

    if (!name.trim() || name.length > 255)
      errs.name = "Project name must be 1–255 characters";

    if (!description || description.length > 1000)
      errs.description = "Description must be 1–1000 characters";

    if (!taskId.length) errs.task = "Please select a task"; // array check

    if (!startDate)
      errs.startDate = "Start date is required";
    else if (new Date(startDate) < new Date().setHours(0, 0, 0, 0))
      errs.startDate = "Start date must be today or future";

    if (!endDate)
      errs.endDate = "End date is required";
    else if (new Date(endDate) < new Date(startDate))
      errs.endDate = "End date must be after start date";

    setErrors(errs);
    return Object.keys(errs).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSuccess("");
    if (!validate()) return;

    const dto = {
      name,
      description,
      task: taskId, // now an array of selected task IDs
      startDate,
      endDate,
      managerId: managerId || null,
    };

    try {
      setLoading(true);
      await axios.post(`${BASE_URL}/projects`, dto);
      setSuccess("Project created successfully!");
      setName("");
      setDescription("");
      setTaskId([]);
      setStartDate("");
      setEndDate("");
      setManagerId("");
    } catch (err) {
      setErrors({
        api: err.response?.data?.message || "Failed to create project",
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <>
      <form
        onSubmit={handleSubmit}
        className="max-w-xl mx-auto p-6 bg-white rounded-xl shadow space-y-4"
      >
        <h2 className="text-2xl font-bold text-gray-800">Create Project</h2>

        {errors.api && (
          <p className="text-red-600 bg-red-100 p-2 rounded">{errors.api}</p>
        )}
        {success && (
          <p className="text-green-600 bg-green-100 p-2 rounded">{success}</p>
        )}

        {/* Name */}
        <div>
          <label className="font-medium">Project Name *</label>
          <input
            className="w-full border p-2 rounded"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
          {errors.name && <p className="text-red-600 text-sm">{errors.name}</p>}
        </div>

        {/* Description */}
        <div>
          <label className="font-medium">Description *</label>
          <textarea
            className="w-full border p-2 rounded"
            rows={4}
            value={description}
            onChange={(e) => setDescription(e.target.value)}
          />
          {errors.description && (
            <p className="text-red-600 text-sm">{errors.description}</p>
          )}
        </div>

        {/* Task */}
        <div>
          <label className="font-medium">Task *</label>
          <select
            className="w-full border p-2 rounded"
            multiple
            value={taskId}
            onChange={(e) => {
              const selected = Array.from(
                e.target.selectedOptions,
                (option) => option.value
              );
              setTaskId(selected);
            }}
          >
            {tasks.map((task) => (
              <option key={task.id} value={task.id}>
                {task.title}
              </option>
            ))}
          </select>
          {errors.task && <p className="text-red-600 text-sm">{errors.task}</p>}
        </div>

        {/* Dates */}
        <div className="grid grid-cols-2 gap-4">
          <div>
            <label className="font-medium">Start Date *</label>
            <input
              type="date"
              className="w-full border p-2 rounded"
              value={startDate}
              onChange={(e) => setStartDate(e.target.value)}
            />
            {errors.startDate && (
              <p className="text-red-600 text-sm">{errors.startDate}</p>
            )}
          </div>

          <div>
            <label className="font-medium">End Date *</label>
            <input
              type="date"
              className="w-full border p-2 rounded"
              value={endDate}
              onChange={(e) => setEndDate(e.target.value)}
            />
            {errors.endDate && (
              <p className="text-red-600 text-sm">{errors.endDate}</p>
            )}
          </div>
        </div>

        {/* Manager ID (optional) */}
        <div>
          <label className="font-medium">Manager ID (optional)</label>
          <input
            className="w-full border p-2 rounded"
            value={managerId}
            onChange={(e) => setManagerId(e.target.value)}
          />
        </div>

        <button
          type="submit"
          disabled={loading}
          className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 disabled:opacity-50"
        >
          {loading ? "Creating..." : "Create Project"}
        </button>
      </form>
      <ProjectList />
    </>
  );
}
