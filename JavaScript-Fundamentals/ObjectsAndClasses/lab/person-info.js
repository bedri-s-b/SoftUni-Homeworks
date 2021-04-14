function persinInfo(firtstName, lastName, age) {
    let person = {};

    person['firstName'] = firtstName; 
    person['lastName'] = lastName;
    person['age'] = age; 

    return person;
};

persinInfo("Peter",
    "Pan",
    "20");