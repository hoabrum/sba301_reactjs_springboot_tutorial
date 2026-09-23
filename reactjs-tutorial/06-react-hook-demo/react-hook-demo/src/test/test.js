function sum(a, b) {
   return a + b;
}

function multiply(a, b) {
   return a * b;
}

console.log("Starting the test...");
setTimeout(() => {
   console.log("Sum:", sum(2, 3));
}, 5000);

console.log("Multiplication:", multiply(4, 5));
console.log("Test completed.");
