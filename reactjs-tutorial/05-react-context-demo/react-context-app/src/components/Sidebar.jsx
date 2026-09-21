import UserPanel from "./UserPanel";

function Sidebar({ user, onLogout }) {
   return (
      <aside className="sidebar">
         <UserPanel user={user} onLogout={onLogout} />
      </aside>
   );
}
export default Sidebar;
