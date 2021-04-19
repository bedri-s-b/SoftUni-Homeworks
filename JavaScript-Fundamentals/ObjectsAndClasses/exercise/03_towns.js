function solve(arr) {


    let townsInfo = arr.map(line => line.split(' | '));

    let towns = [];

    for (const t of townsInfo) {
        let town = {}
        town["town"] = t[0];
        town.latitude = Number(t[1]).toFixed(2);;
        town["longitude"] = Number(t[2]).toFixed(2);

        towns.push(town);

    }

    for (const t of towns) {
        console.log(t);
    }


}
