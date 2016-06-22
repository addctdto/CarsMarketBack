package bg.carsmarket.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "car")
@XmlRootElement
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Long id;
	
	@JoinColumn(name = "MANUFACTURER_ID", nullable = false)
	@NonNull
	private Manufacturer man;
	
	@Column(nullable = false)
	@NonNull
	private String imgUrl;
	
	@Column(nullable = false)
	@NonNull
	private String videoUrl;
	
	@Column(nullable = false)
	@NonNull
	private String model;
	
	@Column(nullable = false)
	@NonNull
	private Double price;
	
	@Column(nullable = false)
	@NonNull
	private Double year;
	
	@Column(nullable = false)
	@NonNull
	private Integer hp;
	
	@ManyToOne
	public Manufacturer getManufacturer() {
		return man;
	}
	
}
