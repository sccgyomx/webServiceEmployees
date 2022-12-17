# Examen practico web service java

### Instrucciones: Observa el siguiente diagrama de BD y realiza los ejercicios que se piden.

<p>
  <img src="https://imgur.com/l64L8J2.png" alt="auth" width="600"/>
</p>
Base de Datos
Deberás implementar una base de datos, puede ser H2, MySQL, Oracle de manera local.

```
CREATE TABLE JOBS(
    ID NUMBER PRIMARY KEY,
    NAME NVARCHAR2(255),
    SALARY NUMBER
);

CREATE TABLE GENDERS(
ID NUMBER PRIMARY KEY ,
NAME NVARCHAR2(255)
);

CREATE TABLE EMPLOYEES(
ID NUMBER PRIMARY KEY ,
GENDER_ID NUMBER,
JOB_ID NUMBER,
NAME NVARCHAR2(255),
LAST_NAME NVARCHAR2(255),
BIRTHDATE DATE,
FOREIGN KEY (GENDER_ID) REFERENCES GENDERS(ID),
FOREIGN KEY (JOB_ID) REFERENCES JOBS(ID)
);

CREATE TABLE EMPLOYEE_WORKED_HOURS(
ID NUMBER PRIMARY KEY ,
EMPLOYEE_ID NUMBER,
WORKED_HOURS NUMBER,
WORKED_DATE DATE
);

INSERT INTO JOBS VALUES (1,'VENTAS',1200);
INSERT INTO JOBS VALUES (2,'RECURSOS HUMANOS',1800);
INSERT INTO JOBS VALUES (3,'PROGRAMACION',12000);

INSERT INTO GENDERS VALUES (1,'MASCULINO');
INSERT INTO GENDERS VALUES (2,'FEMENINO');

INSERT INTO EMPLOYEES VALUES (1,1,1,'PEDRO','GARCIA','28/04/1993');
INSERT INTO EMPLOYEES VALUES (2,2,2,'INES','JERONIMO','28/08/1985');
INSERT INTO EMPLOYEES VALUES (3,1,3,'SAID','CARCAMO','28/04/1993');

INSERT INTO EMPLOYEE_WORKED_HOURS VALUES (1,1,8,'12/11/2022');
INSERT INTO EMPLOYEE_WORKED_HOURS VALUES (2,1,9,'13/11/2022');
INSERT INTO EMPLOYEE_WORKED_HOURS VALUES (3,1,5,'14/11/2022');

INSERT INTO EMPLOYEE_WORKED_HOURS VALUES (4,2,9,'12/11/2022');
INSERT INTO EMPLOYEE_WORKED_HOURS VALUES (5,2,9,'13/11/2022');
INSERT INTO EMPLOYEE_WORKED_HOURS VALUES (6,2,3,'14/11/2022');

INSERT INTO EMPLOYEE_WORKED_HOURS VALUES (7,3,10,'12/11/2022');
INSERT INTO EMPLOYEE_WORKED_HOURS VALUES (8,4,10,'13/11/2022');
INSERT INTO EMPLOYEE_WORKED_HOURS VALUES (9,4,10,'14/11/2022');

COMMIT;
```

### Ejercicio 1. Realiza un web service que permita agregar un nuevo empleado.

Para que se pueda registrar un empleado debe cumplir lo siguiente:

- Respetar el contrato de interfaz (ver anexo 1.1).

<p>
  <img src="https://imgur.com/1BJUiA2.png"/>
</p>
<p>
  <img src="https://imgur.com/FSZv2Ys.png"/>
</p>

- El nombre y apellido del empleado no existan registrados en base de datos.

- Ser mayor de edad.

- El puesto asignado debe existir en la tabla de Jobs.

<p>
  <img src="https://imgur.com/Rlppgvf.png" alt="codigo de metodo guardar"/>
</p>

En esta parte nos apollamos creando una lista apartir de la lista de empleados y filtrado en ella el nombre y apellido del nuevo empleado en caso de encontrar coincidencias el tamño de la nueva lista sera mayor a 0 y manda el mensaje correspondiente, para la edad obtenemos la propiedad `birthdate` que es una cadena ("12/12/2022") y la separamos con metodo `split("/")`, la transformanos a fecha y obtenemos los años con la clase `Period` y el metodo `getYears()`, por ultimo para validar que el trabajo al que se da de alta existe utilizamos el metodo `existsById()` de `CrudRepository`

codigo:

```
@Override
public Object guardar(Object object) {
    Employee employeeAux =((Employee) object);
    String[] fecha = (employeeAux.getBirthdate()).split("/");
    LocalDate birthDate = LocalDate.of(Integer.parseInt( fecha[2]),Integer.parseInt( fecha[1]),Integer.parseInt( fecha[0]));
    LocalDate currentDate = LocalDate.now();
    Period period = Period.between(birthDate,currentDate);
    int years = period.getYears();
    Response response = new Response(employeeAux.getId(),true,"El empleado se registro correctamente");
    List<Employee> employeeList = ((List<Employee>) employeeDAO.findAll()).stream().filter(
            employee -> employee.getName().equals(((Employee)object).getName())
                    &&
                    employee.getLastName().equals(((Employee)object).getLastName())
    ).toList();
    if(employeeList.size()>0){
        response.setId(null);
        response.setSuccess(false);
        response.setMessage("El empleado ya existe");
    }else if(years<18){
        response.setId(null);
        response.setSuccess(false);
        response.setMessage("Los empleados deben ser mayores de 18 años");
    }else if(jobDAO.existsById(((Employee) object).getJob().getId())){
        employeeDAO.save((Employee) object);
    }else {
        response.setId(null);
        response.setSuccess(false);
        response.setMessage("El trabajo asignado no existe");
    }
    return response;
}
```

### Ejercicio 2. Realiza un web service que permita:

a) Recibir un puesto
b) Consultar los empleados por puesto
c) Filtar los empleados obtenidos en b) con el puesto recibido en a) ocupando
expresiones lambda con filtro
d) De los empleados obtenidos en c) ordenarlos por appellido

<p>
  <img src="https://imgur.com/gAijqfp.png" alt="codigo de metodo buscar"/>
</p>

Implemente la solucion en el siguiente metodo.

<p>
  <img src="https://imgur.com/DptMXSv.png" alt="codigo de metodo buscar"/>
</p>

Creamos una nueva lista para guardar las coincidencias, ya que nostraeremos los registros de la tabla employes y aplicaremos un filtro sobre los datos que quieramos buscar en el ejercicio nos pide el de trabajo y lo buscaremos por id.

Posteriormente utilizamos el metodo `short` para ordenarlos por apeido `lastName` por ultimo devolverlos.

Codigo:

```
@Override
public List<Object> buscar(Object object) {
    List<Employee> employeeList = new java.util.ArrayList<>(((List<Employee>) employeeDAO.findAll()).stream().filter(
            employee ->
                    employee.getId() == ((Employee) object).getId()
                            ||
                            employee.getBirthdate().equals(((Employee) object).getBirthdate())
                            ||
                            employee.getName().equals(((Employee) object).getName())
                            ||
                            employee.getLastName().equals(((Employee) object).getLastName())
                            ||
                            employee.getJob().getId() == ((Employee) object).getJob().getId()
    ).toList());
    employeeList.sort(Comparator.comparing(Employee::getLastName));
    return Collections.singletonList(employeeList);
}
```
