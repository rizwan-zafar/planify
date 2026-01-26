import { useState } from "react";
import axios from "axios";
const BASE_URL = import.meta.env.VITE_API_BASE_URL;
const TASK_PRIORITIES = ["LOW", "MEDIUM", "HIGH"]; // TaskPriority enum
import TaskList from "./TaskList.jsx"
export default function CreateTaskForm() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [priority, setPriority] = useState("");
  const [errors, setErrors] = useState({});
  const [loading, setLoading] = useState(false);
  const [successMsg, setSuccessMsg] = useState("");

  const validate = () => {
    const errs = {};

    if (!title.trim()) errs.title = "Title is required";
    else if (title.length > 255) errs.title = "Title must be <= 255 chars";

    if (description.length > 1000)
      errs.description = "Description must be <= 1000 chars";

    if (!dueDate) errs.dueDate = "Due date is required";
    else if (new Date(dueDate) < new Date(new Date().setHours(0, 0, 0, 0)))
      errs.dueDate = "Due date cannot be in the past";

    if (!priority) errs.priority = "Priority is required";

    setErrors(errs);
    return Object.keys(errs).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSuccessMsg("");
    if (!validate()) return;

    const dto = { title, description: description || null, dueDate, priority };

    try {
      setLoading(true);
      const response = await axios.post(`${BASE_URL}/tasks`, dto);

      setSuccessMsg("Task created successfully!");
      // Reset form
      setTitle("");
      setDescription("");
      setDueDate("");
      setPriority("");
    } catch (err) {
      console.error(err);
      setErrors({
        api: err.response?.data?.message || "Something went wrong",
      });
    } finally {
      setLoading(false);
    }
  };

  return (
      <>
    <form
      onSubmit={handleSubmit}
      className="max-w-md mx-auto p-6 bg-white rounded-xl shadow-md space-y-4"
    >
      <h2 className="text-2xl font-bold text-gray-800">Create Task</h2>

      {errors.api && (
        <p className="text-red-600 text-sm bg-red-100 p-2 rounded">{errors.api}</p>
      )}
      {successMsg && (
        <p className="text-green-600 text-sm bg-green-100 p-2 rounded">{successMsg}</p>
      )}

      {/* Title */}
      <div>
        <label className="block font-medium text-gray-700">Title *</label>
        <input
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          className="mt-1 w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring focus:border-blue-500"
        />
        {errors.title && <p className="text-red-600 text-sm mt-1">{errors.title}</p>}
      </div>

      {/* Description */}
      <div>
        <label className="block font-medium text-gray-700">Description</label>
        <textarea
          value={description}
          onChange={(e) => setDescription(e.target.value)}
          className="mt-1 w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring focus:border-blue-500"
          rows={4}
        ></textarea>
        {errors.description && (
          <p className="text-red-600 text-sm mt-1">{errors.description}</p>
        )}
      </div>

      {/* Due Date */}
      <div>
        <label className="block font-medium text-gray-700">Due Date *</label>
        <input
          type="date"
          value={dueDate}
          onChange={(e) => setDueDate(e.target.value)}
          className="mt-1 w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring focus:border-blue-500"
        />
        {errors.dueDate && (
          <p className="text-red-600 text-sm mt-1">{errors.dueDate}</p>
        )}
      </div>

      {/* Priority */}
      <div>
        <label className="block font-medium text-gray-700">Priority *</label>
        <select
          value={priority}
          onChange={(e) => setPriority(e.target.value)}
          className="mt-1 w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring focus:border-blue-500"
        >
          <option value="">Select priority</option>
          {TASK_PRIORITIES.map((p) => (
            <option key={p} value={p}>
              {p}
            </option>
          ))}
        </select>
        {errors.priority && (
          <p className="text-red-600 text-sm mt-1">{errors.priority}</p>
        )}
      </div>

      <button
        type="submit"
        disabled={loading}
        className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition disabled:opacity-50"
      >
        {loading ? "Creating..." : "Create Task"}
      </button>
    </form>
 <TaskList />
 </>

  );
}
