import { useState } from "react";
/*let count = 0;
function increment() {
   count = count + 1;
   console.log(count);
}*/

//const [count, setCount] = useState(0);
export default function Counter() {
   const [count, setCount] = useState(0);

   const increment = () => {
      //setCount((x) => x + 1);
      //setCount((x) => x + 1);
      //setCount((x) => x + 1);

      //callack function
      setTimeout(() => {
         setCount((x) => x + 1);
      }, 1000);

      //int a = 100
      //a++
      //++a
      //setCount(count + 1);
      //setCount(count + 1);
      //setCount(count + 1);
      console.log("Count =" + count);
   };

   function increment2() {
      setCount(count + 1);
   }

   return (
      <div>
         <p>Count: {count}</p>
         <button onClick={increment}>Increment</button>
      </div>
   );
}
