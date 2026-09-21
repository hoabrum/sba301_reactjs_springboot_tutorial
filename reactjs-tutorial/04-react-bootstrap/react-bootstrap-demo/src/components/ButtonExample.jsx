import Button from "react-bootstrap/Button";
export default function ButtonExample() {
   return (
      <>
         <button
            className="btn btn-primary"
            style={{ backgroundColor: "black", color: "yellow" }}
         >
            Click me
         </button>
         <Button variant="warning">Primary</Button>
      </>
   );
}
