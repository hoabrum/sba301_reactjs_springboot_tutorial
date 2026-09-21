//var year = "2026";
//var age = 18;
//var gpa = 9.5;

//console.log(year + age + gpa);
//console.log(age + gpa);
//console.log(parseInt(year) + age + gpa);

var num1 = 2026;
var num2 = "2026";

//console.log(num1 == num2); //true
//console.log(num1 === num2); //false

var str1 = new String("Hello");
var str2 = new String("Hello");

//console.log(str1 == str2); //false
//console.log(str1 === str2); //false

var str3 = "Hello";
var str4 = "Hello World";
str4 = str3;
//console.log(str3 >= str4); //false

var str5; //undefined
console.log(str5);
str5 = "FU";

console.log(str5);

//Java - String str5;//null
//Java - int num;//0

for (let i = 0; i < 3; i++) {
   setTimeout(() => {
      console.log(i);
   }, 1000);
}

let name1 = new String("Hello");
let name2 = new String("Hello");

//console.log(name1 == name2); //false
//console.log(name1 === name2); //false

//console.log(name1.valueOf() == name2.valueOf()); //true

let addr1 = "Thach That";
//addr1 += "Ha Noi";
addr1 = addr1.concat(2025);

console.log(addr1);
