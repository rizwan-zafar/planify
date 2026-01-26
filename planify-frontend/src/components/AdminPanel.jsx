import { useState } from "react";
import TaskList from "./TaskList.jsx";
import ProjectList from "./ProjectList.jsx";
import ManagerList from "./ManagerList.jsx";
import CreateTask from "./CreateTaskForm.jsx";
import CreateProject from "./CreateProjectForm.jsx";
import CreateManager from "./CreateManagerForm.jsx";

export default function AdminPanel() {
  const [activeTab, setActiveTab] = useState("Tasks");
  const [showCreate, setShowCreate] = useState(false);

  const renderContent = () => {
    switch (activeTab) {
      case "Tasks":
        return showCreate ? (
          <CreateTask onClose={() => setShowCreate(false)} />
        ) : (
          <>
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-xl font-bold">📋 Tasks</h2>
              <button
                onClick={() => setShowCreate(true)}
                className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
              >
                ➕ Add Task
              </button>
            </div>
            <TaskList />
          </>
        );

      case "Projects":
        return showCreate ? (
          <CreateProject onClose={() => setShowCreate(false)} />
        ) : (
          <>
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-xl font-bold">📁 Projects</h2>
              <button
                onClick={() => setShowCreate(true)}
                className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
              >
                ➕ Add Project
              </button>
            </div>
            <ProjectList />
          </>
        );

      case "Managers":
        return showCreate ? (
          <CreateManager onClose={() => setShowCreate(false)} />
        ) : (
          <>
            <div className="flex justify-between items-center mb-4">
              <h2 className="text-xl font-bold">👔 Managers</h2>
              <button
                onClick={() => setShowCreate(true)}
                className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
              >
                ➕ Add Manager
              </button>
            </div>
            <ManagerList />
          </>
        );

      default:
        return null;
    }
  };

  return (
    <div className="flex h-screen bg-gray-100">
      {/* Sidebar */}
      <div className="w-64 bg-white shadow-lg flex flex-col">
        <div className="p-6 text-2xl font-bold border-b">Planify Admin</div>
        <nav className="flex-1 p-4 flex flex-col gap-2">
          {["Tasks", "Projects", "Managers"].map((tab) => (
            <button
              key={tab}
              onClick={() => {
                setActiveTab(tab);
                setShowCreate(false);
              }}
              className={`text-left px-4 py-2 rounded hover:bg-blue-100 transition ${
                activeTab === tab ? "bg-blue-600 text-white" : "text-gray-700"
              }`}
            >
              {tab}
            </button>
          ))}
        </nav>
      </div>

      {/* Main content */}
      <div className="flex-1 p-6 overflow-auto">{renderContent()}</div>
    </div>
  );
}
