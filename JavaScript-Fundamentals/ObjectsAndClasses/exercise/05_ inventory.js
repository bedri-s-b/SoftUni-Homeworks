function solve(arr) {

    let heros = [];

    let herosInfo = arr.map(i => i.split(' / '));

    herosInfo.forEach(i => {
        let item = i[2].split(', ');
        item.sort();
        let hero = {
            hero: i[0],
            level: +i[1],
            items: item

        };

        heros.push(hero);
    });

    heros.sort((a, b) => a.level - b.level)
        .forEach(h => {
                        console.log(`Hero: ${h['hero']}`)
            console.log(`level => ${h.level}`);
            console.log(`items => ${h.items.join(", ")}`);
        });



}
