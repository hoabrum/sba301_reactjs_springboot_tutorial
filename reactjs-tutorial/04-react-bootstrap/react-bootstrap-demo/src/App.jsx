import "./App.css";
import Card from "./components/Card";
import Counter from "./components/Counter";

function App() {
   return (
      <>
         <Counter />
         <Card title="Card Title">
            <h4>Card Heading 4</h4>
            <p>This is a card component.</p>
            <h3>Card Heading 3</h3>
            <li>List Item 1</li>
            <li>List Item 2</li>
         </Card>
      </>
   );
}

export default App;
