function solve(arr) {

    let power = {
        '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '10': 10, 'J': 11, 'Q': 12, 'K': 13, 'A': 14
    };

    let type = {
        S: 4,
        H: 3,
        D: 2,
        C: 1
    };

    let players = new Map();

    for (const cart of arr) {
        let [name, carts] = cart.split(': ');
        carts = carts.split(', ')

        if (!players.has(name)) {
            players.set(name, []);
        }
        carts.forEach(c => {
            if (!players.get(name).includes(c)) {
                players.get(name).push(c)
            }
        })
    }

    [...players].forEach(p => {
        let result = sumAllCarts(p[1]);
        console.log(`${p[0]}: ${result}`);
    })

    function sumAllCarts(arr) {
        let total = 0;

        for (const cart of arr) {
            let p = '';
            let t = '';
            if (cart.length > 2) {
                p = cart.split('').slice(0, 2).join('');
                t = cart.split('').slice(2).toString();
            } else {
                p = cart.split('').slice(0, 1).toString();
                t = cart.split('').slice(1).toString();
            }

            total += +power[p] * +type[t];

        }
        return total;
    };

}

solve([
    'Peter: 2C, 4H, 9H, AS, QS',
    'Tomas: 3H, 10S, JC, KD, 5S, 10S',
    'Andrea: QH, QC, QS, QD',
    'Tomas: 6H, 7S, KC, KD, 5S, 10C',
    'Andrea: QH, QC, JS, JD, JC',
    'Peter: JD, JD, JD, JD, JD, JD'
]);