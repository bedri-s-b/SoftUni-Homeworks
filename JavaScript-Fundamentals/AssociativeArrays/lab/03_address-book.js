function solve(arr) {
    let stores = {};

    arr.forEach(i => {
        let [name, address] = i.split(':');
        stores[name] = address;
    });

    let result = [...Object.entries(stores)];

    result.sort((a, b) => String(a[0]).localeCompare(b[0]));

    Object.values(result).forEach(i => console.log(`${i[0]} -> ${i[1]}`))


}

solve(['Tim:Doe Crossing',
    'Bill:Nelson Place',
    'Peter:Carlyle Ave',
    'Bill:Ornery Rd']);