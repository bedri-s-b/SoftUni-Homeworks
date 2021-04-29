function solve(arr) {

    let db = [];

    let countrys = {}

    for (const line of arr) {
        let [country, town, price] = line.split(' > ');
        if (!countrys.hasOwnProperty(country)) {
            countrys[country] = {};
            countrys[country][town] = +price;
        } else {
            if (!countrys[country].hasOwnProperty(town)) {
                countrys[country][town] = +price
            } else {
                let current = +price;
                let oddPrice = countrys[country][town];
                let minPrice = Math.min(current, oddPrice);
                countrys[country][town] = minPrice;
            }
        }
    }

    let print = Object.entries(countrys).sort((a, b) => a[0].localeCompare(b[0]));

    for (const el of print) {
        let result = '';
        result = el[0] + ' -> ';
        Object.entries(el[1]).sort((a, b) => a[1] - b[1])
            .forEach(e => result += e[0] + ' -> ' + e[1] + ' ');
        console.log(result.trim());
    }
}
