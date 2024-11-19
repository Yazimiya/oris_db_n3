public class Psychologist {
    private Long id;
    private String name;
    private String surname;
    private Integer experience;
    private Integer age;
    private String specialization;
    private String phone;


    public Psychologist(Long id, String name, String surname, Integer experience, Integer age, String specialization, String phone) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.experience = experience;
        this.age = age;
        this.specialization = specialization;
        this.phone = phone;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getExperience() {
        return experience;
    }

    public Integer getAge() {
        return age;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getPhone() {
        return phone;
    }
}
