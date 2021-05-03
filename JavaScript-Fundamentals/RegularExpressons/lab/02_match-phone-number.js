function solve(input) {

    let pattern = /\+359([ -])2\1[0-9]{3}\1[0-9]{4}\b/g;

    let numbers = [];


    while ((number = pattern.exec(input)) !== null) {
        numbers.push(number[0])
    }

    console.log(numbers.join(", "));

}

solve("+359 2 222 2222,359-2-222-2222, +359/2/222/2222, +359-2 222 2222 +359 2-222-2222, +359-2-222-222, +359-2-222-22222 +359-2-222-2222"
)