function solve(arr) {

    let resourceAndQuantity = {};

    let i = 0;
    let current = '';

    arr.forEach(e => {
        if (i % 2 === 0) {
            if (!resourceAndQuantity.hasOwnProperty(e)) {
                resourceAndQuantity[e] = 0;
            }
            current = e;
        } else {
            resourceAndQuantity[current] = +e + +resourceAndQuantity[current];
        }
        i++;
    })

    Object.entries(resourceAndQuantity)
        .forEach(e => console.log(`${e[0]} -> ${e[1]}`))

}

solve([
    'Gold',
    '155',
    'Silver',
    '10',
    'Copper',
    '17'
])