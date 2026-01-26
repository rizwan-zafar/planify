import { useEffect, useState } from "react";
import axios from "axios";

const BASE_URL = import.meta.env.VITE_API_BASE_URL;

const PRIORITIES = ["LOW", "MEDIUM", "HIGH"];
const STATUSES = ["OPEN", "IN_PROGRESS", "COMPLETE"];

export default function TaskList() {
  const [tasks, setTasks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [editingTask, setEditingTask] = useState(null);

  const fetchTasks = async () => {
    try {
      setLoading(true);
      const res = await axios.get(`${BASE_URL}/tasks`);
      setTasks(res.data);
    } catch {
      setError("Failed to fetch tasks");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  const deleteTask = async (id) => {
    if (!confirm("Are you sure you want to delete this task?")) return;

    try {
      await axios.delete(`${BASE_URL}/tasks/${id}`);
      setTasks(tasks.filter((t) => t.id !== id));
    } catch {
      alert("Failed to delete task");
    }
  };

  const updateTask = async () => {
    try {
      await axios.put(`${BASE_URL}/tasks/${editingTask.id}`, {
        title: editingTask.title,
        description: editingTask.description || null,
        dueDate: editingTask.dueDate,
        priority: editingTask.priority,
        status: editingTask.status,
      });
      setEditingTask(null);
      fetchTasks();
    } catch {
      alert("Failed to update task");
    }
  };

  if (loading)
    return <p className="text-center mt-10 text-gray-500 text-lg">Loading tasks...</p>;
  if (error)
    return <p className="text-center text-red-600 mt-10 text-lg">{error}</p>;

  return (
    <div className="max-w-6xl mx-auto mt-8">
      {tasks.length === 0 ? (
        <div className="text-center py-20 border-dashed border-4 border-gray-300 rounded-xl bg-gray-50">
          <p className="text-2xl font-bold text-gray-400 mb-2">😕 No tasks found!</p>
          <p className="text-gray-500">Add a new task to get started.</p>
        </div>
      ) : (
        <div className="overflow-x-auto">
          <table className="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
            <thead className="bg-blue-600 text-white text-left">
              <tr>
                <th className="py-3 px-4">Title</th>
                <th className="py-3 px-4">Description</th>
                <th className="py-3 px-4">Due Date</th>
                <th className="py-3 px-4">Priority</th>
                <th className="py-3 px-4">Status</th>
                <th className="py-3 px-4">Actions</th>
              </tr>
            </thead>
            <tbody>
              {tasks.map((task) => (
                <tr
                  key={task.id}
                  className="border-b hover:bg-gray-50 transition"
                >
                  <td className="py-3 px-4">{task.title}</td>
                  <td className="py-3 px-4">{task.description || "-"}</td>
                  <td className="py-3 px-4">{task.dueDate}</td>
                  <td className="py-3 px-4">{task.priority}</td>
                  <td className="py-3 px-4">{task.status}</td>
                  <td className="py-3 px-4 flex gap-2">
                    <button
                      onClick={() => setEditingTask(task)}
                      className="px-3 py-1 text-sm bg-blue-500 text-white rounded hover:bg-blue-600"
                    >
                      Edit
                    </button>
                    <button
                      onClick={() => deleteTask(task.id)}
                      className="px-3 py-1 text-sm bg-red-500 text-white rounded hover:bg-red-600"
                    >
                      Delete
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {/* EDIT MODAL */}
      {editingTask && (
        <div className="fixed inset-0 bg-black/40 flex items-center justify-center z-50">
          <div className="bg-white p-6 rounded-xl w-full max-w-md space-y-3 shadow-lg">
            <h2 className="text-xl font-bold">Edit Task</h2>

            <input
              className="w-full border p-2 rounded"
              value={editingTask.title}
              onChange={(e) =>
                setEditingTask({ ...editingTask, title: e.target.value })
              }
              placeholder="Title"
            />

            <textarea
              className="w-full border p-2 rounded"
              value={editingTask.description || ""}
              onChange={(e) =>
                setEditingTask({
                  ...editingTask,
                  description: e.target.value,
                })
              }
              placeholder="Description"
            />

            <input
              type="date"
              className="w-full border p-2 rounded"
              value={editingTask.dueDate}
              onChange={(e) =>
                setEditingTask({ ...editingTask, dueDate: e.target.value })
              }
            />

            <select
              className="w-full border p-2 rounded"
              value={editingTask.priority}
              onChange={(e) =>
                setEditingTask({ ...editingTask, priority: e.target.value })
              }
            >
              {PRIORITIES.map((p) => (
                <option key={p} value={p}>
                  {p}
                </option>
              ))}
            </select>

            <select
              className="w-full border p-2 rounded"
              value={editingTask.status}
              onChange={(e) =>
                setEditingTask({ ...editingTask, status: e.target.value })
              }
            >
              {STATUSES.map((s) => (
                <option key={s} value={s}>
                  {s}
                </option>
              ))}
            </select>

            <div className="flex justify-end gap-2 pt-2">
              <button
                onClick={() => setEditingTask(null)}
                className="px-4 py-2 bg-gray-300 rounded hover:bg-gray-400"
              >
                Cancel
              </button>
              <button
                onClick={updateTask}
                className="px-4 py-2 bg-green-600 text-white rounded hover:bg-green-700"
              >
                Update
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
