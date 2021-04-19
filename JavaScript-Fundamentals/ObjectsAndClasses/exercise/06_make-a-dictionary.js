function dictionary(input) {
    let dictonaryInfo = input.map(e => JSON.parse(e));

    let result = {};

    dictonaryInfo.forEach(e => {
        let key = Object.keys(e)[0];
        let velue = Object.values(e)[0];

        result[key] = velue;
    });

    let keys = Object.keys(result).sort();

    for (let i = 0; i < keys.length; i++) {
        console.log(`Term: ${keys[i]} => Definition: ${result[keys[i]]}`);
    }


}
