package co.tylermaxwell.belttemplatre.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Thing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String propertyOne;

    @NotBlank
    private String propertyTwo;
    @Min(value = 1, message = "message string")
    private int count;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "things_otherthings",
            joinColumns = @JoinColumn(name = "thing_id"),
            inverseJoinColumns = @JoinColumn(name = "otherthing_id")
    )
    private List<OtherThing> otherThings;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;


}
