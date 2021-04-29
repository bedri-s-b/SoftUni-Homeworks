function solve(guests) {
    let reservations = {
        vip: new Set(),
        regular: new Set()
    }

    for (const guest of guests) {
        if (guest === 'PARTY') {
            break;
        }
        let check = Number(guest[0]);
        if (check >= 0 || check <= 0) {
            reservations.vip.add(guest);
        } else {
            reservations.regular.add(guest);
        }

    }
    let index = guests.indexOf('PARTY');
    let rest = guests.slice(index + 1);

    rest.forEach(e => {
        if (reservations.vip.has(e)) {
            reservations.vip.delete(e);
        } else if (reservations.regular.has(e)) {
            reservations.regular.delete(e);
        }

    })
    let vipResult = [...reservations.vip.values()];
    let regularResult = [...reservations.regular.values()];

    console.log(vipResult.length + regularResult.length);
    printArr(vipResult);
    printArr(regularResult);

    function printArr(arr) {
        arr.forEach(e => console.log(e))
    }
}
