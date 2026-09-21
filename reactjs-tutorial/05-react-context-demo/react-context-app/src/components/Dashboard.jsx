import Sidebar from "./Sidebar";

function Dashboard({ user, onLogout }) {
   return (
      <div className="dashboard">
         <h2>Dashboard</h2>
         <Sidebar user={user} onLogout={onLogout} />
      </div>
   );
}
export default Dashboard;
