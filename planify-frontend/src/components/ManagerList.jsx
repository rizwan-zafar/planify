import { useEffect, useState } from "react";
import axios from "axios";

const API_BASE = import.meta.env.VITE_API_BASE_URL;
const GENDERS = ["MALE", "FEMALE"];

export default function ManagerList() {
  const [managers, setManagers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [editingManager, setEditingManager] = useState(null);
  const [viewingManager, setViewingManager] = useState(null);
  const [formData, setFormData] = useState({});

  // Fetch managers
  const fetchManagers = async () => {
    try {
      setLoading(true);
      const res = await axios.get(`${API_BASE}/managers`);
      setManagers(res.data);
    } catch {
      setError("Failed to fetch managers");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchManagers();
  }, []);

  // Delete manager
  const deleteManager = async (id) => {
    if (!confirm("Are you sure you want to delete this manager?")) return;

    try {
      await axios.delete(`${API_BASE}/managers?id=${id}`);
      setManagers(managers.filter((m) => m.id !== id));
    } catch {
      alert("❌ Failed to delete manager");
    }
  };

  // Start editing
  const startEdit = (manager) => {
    setEditingManager(manager);
    setFormData({ ...manager });
  };

  const handleChange = (e) =>
    setFormData({ ...formData, [e.target.name]: e.target.value });

  // Update manager
  const updateManager = async () => {
    try {
      const queryParams = new URLSearchParams({
        id: editingManager.id,
        ...formData,
      }).toString();
      await axios.put(`${API_BASE}/managers?${queryParams}`);
      setEditingManager(null);
      fetchManagers();
    } catch {
      alert("❌ Failed to update manager");
    }
  };

  if (loading)
    return <p className="text-center mt-10 text-gray-500 text-lg">Loading managers...</p>;
  if (error)
    return <p className="text-center mt-10 text-red-600 text-lg">{error}</p>;

  return (
    <div className="max-w-4xl mx-auto mt-8">
      {managers.length === 0 ? (
        <div className="text-center py-20 border-dashed border-4 border-gray-300 rounded-xl bg-gray-50">
          <p className="text-2xl font-bold text-gray-400 mb-2">😕 No managers found!</p>
          <p className="text-gray-500">Add a new manager to get started.</p>
        </div>
      ) : (
        <div className="overflow-x-auto">
          <table className="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
            <thead className="bg-blue-600 text-white text-left">
              <tr>
                <th className="py-3 px-4">Name</th>
                <th className="py-3 px-4">Email</th>
                <th className="py-3 px-4">Department</th>
                <th className="py-3 px-4">Gender</th>
                <th className="py-3 px-4">Actions</th>
              </tr>
            </thead>
            <tbody>
              {managers.map((manager) => (
                <tr key={manager.id} className="border-b hover:bg-gray-50 transition">
                  <td className="py-3 px-4">{manager.name}</td>
                  <td className="py-3 px-4">{manager.email}</td>
                  <td className="py-3 px-4">{manager.department}</td>
                  <td className="py-3 px-4">{manager.gender}</td>
                  <td className="py-3 px-4 flex gap-2">
                    <button
                      onClick={() => startEdit(manager)}
                      className="px-3 py-1 text-sm bg-blue-500 text-white rounded hover:bg-blue-600"
                    >
                      ✏️ Edit
                    </button>
                    <button
                      onClick={() => deleteManager(manager.id)}
                      className="px-3 py-1 text-sm bg-red-500 text-white rounded hover:bg-red-600"
                    >
                      🗑️ Delete
                    </button>
                    <button
                      onClick={() => setViewingManager(manager)}
                      className="px-3 py-1 text-sm bg-gray-500 text-white rounded hover:bg-gray-600"
                    >
                      👁 View
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {/* EDIT MODAL */}
      {editingManager && (
        <div className="fixed inset-0 bg-black/40 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded-xl w-full max-w-md space-y-3 shadow-lg">
            <h2 className="text-xl font-bold">Edit Manager</h2>

            <input
              name="name"
              placeholder="Name"
              value={formData.name}
              onChange={handleChange}
              className="w-full border p-2 rounded"
            />
            <input
              name="email"
              type="email"
              placeholder="Email"
              value={formData.email}
              onChange={handleChange}
              className="w-full border p-2 rounded"
            />
            <input
              name="department"
              placeholder="Department"
              value={formData.department}
              onChange={handleChange}
              className="w-full border p-2 rounded"
            />
            <select
              name="gender"
              value={formData.gender}
              onChange={handleChange}
              className="w-full border p-2 rounded"
            >
              {GENDERS.map((g) => (
                <option key={g} value={g}>{g}</option>
              ))}
            </select>

            <div className="flex justify-end gap-2 pt-2">
              <button
                onClick={() => setEditingManager(null)}
                className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
              >
                Cancel
              </button>
              <button
                onClick={updateManager}
                className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700"
              >
                Update
              </button>
            </div>
          </div>
        </div>
      )}

      {/* VIEW DETAILS MODAL */}
      {viewingManager && (
        <div className="fixed inset-0 bg-black/40 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded-xl w-full max-w-2xl space-y-4 shadow-lg">
            <h2 className="text-2xl font-bold">{viewingManager.name}</h2>

            <div className="text-sm text-gray-600 space-y-1">
              <p><b>Email:</b> {viewingManager.email}</p>
              <p><b>Department:</b> {viewingManager.department}</p>
              <p><b>Gender:</b> {viewingManager.gender}</p>
            </div>

            <hr />

            <h3 className="text-lg font-semibold">Projects</h3>

            {viewingManager.projects?.length ? (
              viewingManager.projects.map((project) => (
                <div key={project.id} className="border rounded p-4 bg-gray-50 space-y-2">
                  <p><b>Project:</b> {project.name}</p>
                  <p>{project.description}</p>
                  <p className="text-sm text-gray-500">
                    📅 {project.startDate} → {project.endDate} | {project.status}
                  </p>

                  {project.task && project.task.length ? (
                    project.task.map((t) => (
                      <div key={t.id} className="mt-2 border rounded p-3 bg-white space-y-1">
                        <p className="font-medium">Task</p>
                        <p><b>Title:</b> {t.title}</p>
                        <p>{t.description}</p>
                        <p className="text-sm text-gray-500">
                          Due: {t.dueDate} | {t.priority} | {t.status}
                        </p>
                      </div>
                    ))
                  ) : (
                    <p className="text-gray-400">No tasks assigned</p>
                  )}
                </div>
              ))
            ) : (
              <p className="text-gray-400">No projects assigned</p>
            )}

            <div className="flex justify-end">
              <button
                onClick={() => setViewingManager(null)}
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
