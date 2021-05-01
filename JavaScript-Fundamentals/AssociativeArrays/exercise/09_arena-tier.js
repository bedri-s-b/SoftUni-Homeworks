function solve(arr) {
    let gladiators = {};

    for (const l of arr) {
        let [name, techniqueskill, skill] = l.split(' -> ')
        if (name === 'Ave Cesar') {
            break;
        } else if (l.split(' -> ').length < 2) {
            let [nameOne, nameTwo] = l.split(' vs ');
            if (gladiators.hasOwnProperty(nameOne) && gladiators.hasOwnProperty(nameTwo)) {
                let sameSkillsOne = Object.entries(gladiators[nameOne]);
                let sameSkillTwo = Object.entries(gladiators[nameTwo]);
                let sameSkill = '';

                sameSkillsOne.forEach(s => {
                    sameSkillTwo.forEach(s2 => {
                        if (s[0] == s2[0]) {
                            sameSkill = s[0];
                        }
                    })
                })
                if (sameSkill !== '') {
                    let win = gladiators[nameOne][sameSkill] < gladiators[nameOne][sameSkill]
                        ? delete gladiators[nameOne][sameSkill] : delete gladiators[nameTwo][sameSkill];

                    if (Object.values(gladiators[nameOne]).length == 0) {
                        delete gladiators[nameOne]
                    } else if (Object.values(gladiators[nameTwo]).length == 0) {
                        delete gladiators[nameTwo]
                    }
                }
            }

        } else {
            if (!gladiators.hasOwnProperty(name)) {
                gladiators[name] = {};
            }
            if (!gladiators[name].hasOwnProperty(techniqueskill)) {
                gladiators[name][techniqueskill] = +skill;
            } else {
                let oddSkill = gladiators[name][techniqueskill];
                let currentSkill = skill;
                let betterSkill = Math.max(oddSkill, currentSkill);
                gladiators[name][techniqueskill] = betterSkill;
            }
        }
    }

    let winner = {};

    Object.entries(gladiators).map(g => {
        let total = 0;
        Object.values(g[1]).forEach(e => total += e);
        winner[g[0]] = total;
    });

    let keys = Object.entries(winner).sort((a, b) => b[1] - a[1]);

    keys.forEach(e => {
        console.log(`${e[0]}: ${e[1]} skill`);
        Object.entries(gladiators[e[0]]).sort((a, b) => {
            if (b[1] === (a[1])) {
                return a[0].localeCompare(b[0])
            } else {
                return b[1] - a[1]
            }
        })
            .forEach(el => console.log(`- ${el[0]} <!> ${el[1]}`))
    })
    let print = Object.entries(gladiators).map(s => Object.entries(s[1]))

}

solve([
    'Peter -> BattleCry -> 400',
    'Alex -> PowerPunch -> 300',
    'Stefan -> Duck -> 200',
    'Stefan -> Tiger -> 250',
    'Ave Cesar'
]);