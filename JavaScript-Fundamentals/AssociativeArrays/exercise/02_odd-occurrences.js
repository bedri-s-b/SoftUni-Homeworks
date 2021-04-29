function solve(arr) {
    let occurrences = new Map();

    arr.split(' ').forEach(w => {
        let result = w.toLowerCase();
        if (occurrences.has(result)) {
            let a = occurrences.get(result);
            occurrences.set(result, occurrences.get(result) + 1);
        } else {
            occurrences.set(result, 1);
        }
    })

    let print = [];
    [...occurrences.entries()].filter(w => w[1] % 2 != 0).map(el => print.push(el[0]));

    console.log(print.join(' '));



}

solve('Java C# Php PHP Java PhP 3 C# 3 1 5 C#');