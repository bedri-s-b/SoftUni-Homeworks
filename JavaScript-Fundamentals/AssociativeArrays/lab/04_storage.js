function solve(arr) {
    let map = new Map();

    arr.forEach(i => {
        let item = i.split(' ')[0];
        let quantity = Number(i.split(' ')[1]);
        if (map.has(item)) {
            quantity += Number(map.get(item));
        }
        map.set(item, quantity);
    })

    // map.entries((v, k) => console.log(`${k} -> ${v}`))

    map.forEach((v, k) => {
        console.log(k + " -> " + v);
    })
}

