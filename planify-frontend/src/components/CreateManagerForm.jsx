import { useState,useEffect } from "react";
import ManagerList from "./ManagerList.jsx";
import axios from "axios";
const API_BASE = import.meta.env.VITE_API_BASE_URL;
const GENDERS = ["MALE", "FEMALE"];

export default function CreateManager() {
    const [projects, setProjects] = useState([]);
     const [taskId, setTaskId] = useState("");
  const [formData, setFormData] = useState({
    name: "",
    email: "",
    department: "",
    gender: "",
  });

    // Fetch tasks for dropdown
    useEffect(() => {
      axios
        .get(`${API_BASE}/projects`)
        .then((res) => setProjects(res.data))
        .catch(() => setErrors({ api: "Failed to load projects" }));
    }, []);

  const [loading, setLoading] = useState(false);
  const [success, setSuccess] = useState("");
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

 const handleSubmit = async (e) => {
   e.preventDefault();
   setError("");
   setSuccess("");
   setLoading(true);

   try {
     const payload = {
       name: formData.name,
       email: formData.email,
       department: formData.department,
       gender: formData.gender,
       projectIds: taskId ? [taskId] : [], // ✅ ARRAY
     };

     await axios.post(`${API_BASE}/managers`, payload, {
       headers: {
         "Content-Type": "application/json",
         accept: "*/*",
       },
     });

     setSuccess("✅ Manager created successfully");
     setFormData({ name: "", email: "", department: "", gender: "" });
     setTaskId("");
   } catch (err) {
     console.error(err);
     setError(err.response?.data?.message || "Failed to create manager");
   } finally {
     setLoading(false);
   }
 };

  return (
    <>
      <div className="max-w-lg mx-auto p-6 bg-white rounded-xl shadow-md border mb-6">
        <h2 className="text-2xl font-bold mb-6">👤 Create Manager</h2>

        {success && (
          <div className="mb-4 p-3 bg-green-100 text-green-800 rounded">
            {success}
          </div>
        )}

        {error && (
          <div className="mb-4 p-3 bg-red-100 text-red-800 rounded">
            {error}
          </div>
        )}

        <form onSubmit={handleSubmit} className="space-y-4">
          <div>
            <label className="block text-sm font-medium mb-1">Name</label>
            <input
              name="name"
              value={formData.name}
              onChange={handleChange}
              placeholder="John Doe"
              className="w-full border rounded px-3 py-2 focus:outline-none focus:ring focus:ring-blue-200"
              required
              minLength={2}
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Email</label>
            <input
              type="email"
              name="email"
              value={formData.email}
              onChange={handleChange}
              placeholder="john@company.com"
              className="w-full border rounded px-3 py-2 focus:outline-none focus:ring focus:ring-blue-200"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Department</label>
            <input
              name="department"
              value={formData.department}
              onChange={handleChange}
              placeholder="Engineering"
              className="w-full border rounded px-3 py-2 focus:outline-none focus:ring focus:ring-blue-200"
              required
            />
          </div>

          <div>
            <label className="block text-sm font-medium mb-1">Gender</label>
            <select
              name="gender"
              value={formData.gender}
              onChange={handleChange}
              className="w-full border rounded px-3 py-2 focus:outline-none focus:ring focus:ring-blue-200"
              required
            >
              <option value="">Select gender</option>
              {GENDERS.map((g) => (
                <option key={g} value={g}>
                  {g}
                </option>
              ))}
            </select>
          </div>

           {/* Task */}
                <div>
                  <label className="font-medium">Project *</label>
                  <select
                    className="w-full border p-2 rounded"
                    value={taskId}
                    onChange={(e) => setTaskId(e.target.value)}
                  >
                    <option value="">Select Project</option>
                    {projects.map((task) => (
                      <option key={task.id} value={task.id}>
                        {task.name}
                      </option>
                    ))}
                  </select>
                </div>

          <button
            type="submit"
            disabled={loading}
            className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700 transition disabled:opacity-50"
          >
            {loading ? "Creating..." : "➕ Create Manager"}
          </button>
        </form>
      </div>

      {/* Manager List */}
      <ManagerList />
    </>
  );
}
