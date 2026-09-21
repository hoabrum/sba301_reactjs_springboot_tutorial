export default function Greeting() {
   const name = "Bui";
   const currentHour = new Date().getHours();
   return (
      <div>
         <h2>Hello, {name}!</h2>
         <p>{currentHour < 12 ? "Good morning" : "Good afternoon"}</p>
         <p>2 + 2 = {2 + 2}</p>
      </div>
   );
}
