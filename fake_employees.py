from faker import Factory
import unidecode

fakerFR = Factory.create('fr_FR')

def clean_email (lastName,firstName):
    email =  unidecode.unidecode(f"{firstName}.{lastName}@gatling.test".lower())
    return (email)

with open ('gatling-java/src/test/resources/data/employees.csv', mode="w", encoding="utf-8") as emp:
    emp.write("nom,prenom,matricule,dateNaissance,email\n")
    for i in range(1000):
        lastName = fakerFR.last_name()
        firstName = fakerFR.first_name()
        email = clean_email (firstName, lastName)
        employeeId = str(fakerFR.random_number(6))
        birthday = str(fakerFR.date_of_birth(tzinfo=None, minimum_age=22, maximum_age=60))
        emp.write(f"{lastName},{firstName},{employeeId},{birthday},{email}\n")
