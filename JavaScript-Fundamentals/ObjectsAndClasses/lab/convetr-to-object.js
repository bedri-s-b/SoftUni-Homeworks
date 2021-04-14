function solve(jsonStr) {

    let person = JSON.parse(jsonStr);

    for (let prop in person) {
        console.log(`${prop}: ${person[prop]}`);
    }



};

solve('{"name": "George", "age": 40, "town": "Sofia"}');
