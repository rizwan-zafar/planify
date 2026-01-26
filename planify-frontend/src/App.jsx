import { useState } from 'react'
import AdminPanel from "./components/AdminPanel.jsx"
function App() {
  const [count, setCount] = useState(0)

  return (
     <div>
         <AdminPanel />
     </div>
   )
}

export default App
