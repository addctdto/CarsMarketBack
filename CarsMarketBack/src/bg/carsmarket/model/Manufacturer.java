package bg.carsmarket.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "manufacturer")
@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Manufacturer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@Column(nullable = false)
	@NonNull
	private String name;
	
	@Column(nullable = false)
	@NonNull
	private String imgUrl;
	
	@Column(nullable = false)
	@NonNull
	private Double lat;
	
	@Column(nullable = false)
	@NonNull
	private Double lon;
	
	@OneToMany(mappedBy = "man", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Car> cars;
	
	@XmlTransient
	public List<Car> getCars() {
		return cars;
	}

}
