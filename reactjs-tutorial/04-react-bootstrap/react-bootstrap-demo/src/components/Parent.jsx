function Child({ onButtonClick, onButtonClick2 }) {
   return (
      <>
         <button onClick={onButtonClick}>Click me</button>
      </>
   );
}

export default function Parent() {
   const handleClick = () => {
      alert("Button was clicked in the child!");
   };
   const xyz = () => {
      alert("Button 2 was clicked in the child!");
   };
   return <Child onButtonClick2={xyz} onButtonClick={handleClick} />;
}
