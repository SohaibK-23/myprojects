console.log('hello');

function addNumbers(num1, num2)
{
    let sum;
    sum = num1 + num2;
    return sum;
}

console.log(addNumbers(2,5));

//Data types
let yourAge = 18;           //Number
let yourName = 'Bob';       //String
let name = {first:'John', last:'Doe'};   //Object
let truth = false;                       //boolean
let groceries = ['apple', 'banana', 'oranges']   //array
let random;                     //undefined
let nothing = null;             //value null

//Strings in Javascript (common methods)
let fruit = 'banana';
let moreFruits = 'banana\napple';        //new line
console.log(fruit.length);
console.log(fruit.indexOf('nan'));
console.log(fruit.slice(2, 6));
console.log(fruit.replace('ban', '123'));
console.log(fruit.toUpperCase());
console.log(fruit.toLowerCase());
console.log(fruit.charAt(2));
console.log(fruit.split(','));  //separate by a comma
console.log(fruit.split(''));  //separate by characters

//Array in Javascript
//Objects in Javascript
//dictionaries in Python

let student = {
    firstName: 'Sohaib', 
    lastName: 'Khan', 
    age: 22, 
    height: 71,
    studentInfo: function (){
        return this.firstName + '\n' + this.lastName + '\n' + this.age;
    }
};
console.log(student.firstName);
console.log(student.lastName);
student.firstName = 'AntiSohaib';
student.age++;
console.log(student.studentInfo);

//Conditionals, Control flows (if else)
//18-35 is my target demographic
// AND && 
// Or ||

var age = prompt('what is your age?');
if(age >= 18 && age <= 35)
{
    status = 'target demo';
} else {
    status = 'not my audience';
}


//Switch statements
//differentiate between weekday vs. weekend
// 0 Sunday
// 1 Monday
// 2 Tuesday
// 3 Wednesday --> weekday
// 4 Thursday --> weekday
// 5 Friday   --> weekend
// 6 Saturday --> weekend

switch (6)
{
    case 0:
        text = 'weekend';
        break;
    case 5:
        text = 'weekend';
        break;
    case 6:
        text = 'weekend';
        break;
    default:
        text = 'weekday';
}










