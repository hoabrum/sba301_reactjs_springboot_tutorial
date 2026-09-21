export default function Card({ title, children }) {
   return (
      <div className="card">
         <h3>{title}</h3>
         <div className="card-body">{children}</div>
      </div>
   );
}
