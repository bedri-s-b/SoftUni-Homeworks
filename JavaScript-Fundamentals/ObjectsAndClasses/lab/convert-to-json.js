function solve(name, lastName, hairColor){
    let person = {};
    person['name']  = name;
    person['lastName']  = lastName;
    person['hairColor'] = hairColor;

    let jsonPerson = JSON.stringify(person);

    console.log(jsonPerson);
};

solve('George',
    'Jones',
    'Brown');