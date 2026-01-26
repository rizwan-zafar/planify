import { useEffect, useState } from "react";
import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;
const PROJECT_STATUSES = ["OPEN", "CLOSED"];

const statusColors = {
  OPEN: "bg-gray-200 text-gray-800",
  CLOSED: "bg-green-200 text-green-800",
};

export default function ProjectList() {
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [editingProject, setEditingProject] = useState(null);
  const [formData, setFormData] = useState({});
  const [viewingProject, setViewingProject] = useState(null);

  const fetchProjects = async () => {
    try {
      setLoading(true);
      const res = await axios.get(`${BASE_URL}/projects`);
      setProjects(res.data);
    } catch {
      setError("Failed to fetch projects");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchProjects();
  }, []);

  const deleteProject = async (id) => {
    if (!confirm("Are you sure you want to delete this project?")) return;

    try {
      await axios.delete(`${BASE_URL}/projects/${id}`);
      setProjects(projects.filter((p) => p.id !== id));
    } catch {
      alert("Failed to delete project");
    }
  };

  const startEdit = (project) => {
    setEditingProject(project);
    setFormData({
      name: project.name,
      description: project.description,
      startDate: project.startDate,
      endDate: project.endDate,
      status: project.status,
      task: project.task.map((t) => t.id) || [], // ✅ now array of task IDs
      managerId: project.managerId || "",
    });
  };

  const handleChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  const updateProject = async () => {
    try {
      const payload = {
        name: formData.name,
        description: formData.description,
        task: formData.task, // array of IDs
        startDate: formData.startDate,
        endDate: formData.endDate,
        status: formData.status,
        managerId: formData.managerId,
      };

      await axios.put(
        `${BASE_URL}/projects/${editingProject.id}`,
        payload,
        {
          headers: { "Content-Type": "application/json" },
        }
      );

      setEditingProject(null);
      fetchProjects();
    } catch (err) {
      console.error(err);
      alert("Failed to update project");
    }
  };

  if (loading)
    return <p className="text-center mt-10 text-gray-500 text-lg">Loading projects...</p>;
  if (error)
    return <p className="text-center text-red-600 mt-10 text-lg">{error}</p>;

  return (
    <div className="max-w-6xl mx-auto mt-8">
      {projects.length === 0 ? (
        <div className="text-center py-20 border-dashed border-4 border-gray-300 rounded-xl bg-gray-50">
          <p className="text-2xl font-bold text-gray-400 mb-2">😕 No projects found!</p>
          <p className="text-gray-500">Add a new project to get started.</p>
        </div>
      ) : (
        <div className="overflow-x-auto">
          <table className="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
            <thead className="bg-blue-600 text-white text-left">
              <tr>
                <th className="py-3 px-4">Name</th>
                <th className="py-3 px-4">Description</th>
                <th className="py-3 px-4">Start Date</th>
                <th className="py-3 px-4">End Date</th>
                <th className="py-3 px-4">Status</th>
                <th className="py-3 px-4">Actions</th>
              </tr>
            </thead>
            <tbody>
              {projects.map((project) => (
                <tr key={project.id} className="border-b hover:bg-gray-50 transition">
                  <td className="py-3 px-4">{project.name}</td>
                  <td className="py-3 px-4">{project.description || "-"}</td>
                  <td className="py-3 px-4">{project.startDate}</td>
                  <td className="py-3 px-4">{project.endDate}</td>
                  <td className="py-3 px-4">
                    <span className={`text-xs px-2 py-1 rounded-full ${statusColors[project.status]}`}>
                      {project.status}
                    </span>
                  </td>
                  <td className="py-3 px-4 flex gap-2">
                    <button
                      onClick={() => startEdit(project)}
                      className="px-3 py-1 text-sm bg-blue-500 text-white rounded hover:bg-blue-600"
                    >
                      Edit
                    </button>
                    <button
                      onClick={() => deleteProject(project.id)}
                      className="px-3 py-1 text-sm bg-red-500 text-white rounded hover:bg-red-600"
                    >
                      Delete
                    </button>
                    <button
                      onClick={() => setViewingProject(project)}
                      className="px-3 py-1 text-sm bg-gray-500 text-white rounded hover:bg-gray-600"
                    >
                      🔍 Details
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {/* EDIT MODAL */}
      {editingProject && (
        <div className="fixed inset-0 bg-black/40 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded-xl w-full max-w-md space-y-3 shadow-lg">
            <h2 className="text-xl font-bold">Edit Project</h2>

            <input
              name="name"
              placeholder="Project Name"
              value={formData.name}
              onChange={handleChange}
              className="w-full border p-2 rounded"
            />

            <textarea
              name="description"
              placeholder="Description"
              value={formData.description || ""}
              onChange={handleChange}
              className="w-full border p-2 rounded"
            />
<div>
  <label className="font-medium">Task *</label>
  <select
    name="task"
    multiple
    value={formData.task}
    onChange={(e) => {
      const selected = Array.from(e.target.selectedOptions, (option) => option.value);
      setFormData({ ...formData, task: selected });
    }}
    className="w-full border p-2 rounded"
  >
    {projects.flatMap(p => p.task).map((t) => (
      <option key={t.id} value={t.id}>
        {t.title}
      </option>
    ))}
  </select>
</div>
            <div className="grid grid-cols-2 gap-3">
              <input
                type="date"
                name="startDate"
                value={formData.startDate}
                onChange={handleChange}
                className="border rounded px-3 py-2"
              />
              <input
                type="date"
                name="endDate"
                value={formData.endDate}
                onChange={handleChange}
                className="border rounded px-3 py-2"
              />
            </div>

            <select
              name="status"
              value={formData.status}
              onChange={handleChange}
              className="w-full border p-2 rounded"
            >
              {PROJECT_STATUSES.map((s) => (
                <option key={s} value={s}>{s}</option>
              ))}
            </select>

            <div className="flex justify-end gap-2 pt-2">
              <button
                onClick={() => setEditingProject(null)}
                className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
              >
                Cancel
              </button>
              <button
                onClick={updateProject}
                className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700"
              >
                Update
              </button>
            </div>
          </div>
        </div>
      )}

      {/* PROJECT DETAILS MODAL */}
      {viewingProject && (
        <div className="fixed inset-0 bg-black/40 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded-xl w-full max-w-lg space-y-4 shadow-lg">
            <h2 className="text-2xl font-bold mb-2">{viewingProject.name}</h2>
            <p className="text-gray-700">{viewingProject.description}</p>

            <div className="flex justify-between text-sm text-gray-500">
              <span>📅 Start: {viewingProject.startDate}</span>
              <span>🏁 End: {viewingProject.endDate}</span>
              <span>Status: {viewingProject.status}</span>
            </div>

            <hr className="my-2" />

            <h3 className="text-lg font-semibold">Task Details</h3>
            {viewingProject.task && viewingProject.task.length ? (
              viewingProject.task.map((t) => (
                <div key={t.id} className="border p-3 rounded bg-gray-50 space-y-1">
                  <p><b>Title:</b> {t.title}</p>
                  <p><b>Description:</b> {t.description}</p>
                  <p><b>Due Date:</b> {t.dueDate}</p>
                  <p><b>Priority:</b> {t.priority}</p>
                  <p><b>Status:</b> {t.status}</p>
                </div>
              ))
            ) : (
              <p className="text-gray-400">No task assigned</p>
            )}

            <div className="flex justify-end pt-2">
              <button
                onClick={() => setViewingProject(null)}
                className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
              >
                Close
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
