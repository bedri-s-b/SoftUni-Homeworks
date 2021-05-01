function solve(input) {

    let allStock = {
        shards: 0,
        fragments: 0,
        motes: 0
    };

    let sorce = input.split(' ');

    for (let i = 0; i < sorce.length; i += 2) {
        let item = sorce[i + 1].toLowerCase();
        let quantity = +sorce[i];
        allStock[item] = allStock.hasOwnProperty(item) ? allStock[item] + quantity : quantity;

        if (item === 'shards') {
            if (allStock[item] >= 250) {
                allStock[item] -= 250;
                console.log(`Shadowmourne obtained!`);
                break;
            }
        } else if (item === 'fragments') {
            if (allStock[item] >= 250) {
                allStock[item] -= 250;
                console.log(`Valanyr obtained!`);
                break;
            }
        } else if (item === 'motes') {
            if (allStock[item] >= 250) {
                allStock[item] -= 250;
                console.log(`Dragonwrath obtained!`);
                break;
            }
        }

    }

    let importantResources = {};

    Object.entries(allStock).map(el => {
        if (el[0] === 'shards' || el[0] === 'fragments' || el[0] === 'motes') {
            importantResources[el[0]] = el[1]
            delete allStock[el[0]]
        }
    })

    Object.entries(importantResources).sort((a, b) => {
        if (a[1] === b[1]) {
            return a[0].localeCompare(b[0])
        } else {
            return b[1] - a[1];
        }
    }).forEach(el => console.log(`${el[0]}: ${el[1]}`))

    Object.entries(allStock).sort((a, b) => a[0].localeCompare(b[0])).forEach(el => console.log(`${el[0]}: ${el[1]}`))



}

solve('3 Motes 5 stones 5 Shards 6 leathers 255 fragments 7 Shards');