function UserPanel({ user, onLogout }) {
   return (
      <div className="user-panel">
         <p>Welcome, {user.name}!</p>
         <button onClick={onLogout}>Log Out</button>
      </div>
   );
}
export default UserPanel;
