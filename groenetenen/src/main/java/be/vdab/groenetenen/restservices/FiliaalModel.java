package be.vdab.groenetenen.restservices;

import be.vdab.groenetenen.domain.Filiaal;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.EntityLinks;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
class FiliaalModel extends RepresentationModel<FiliaalModel> {
    @SuppressWarnings("unused")
    private Filiaal filiaal;

    FiliaalModel() {
    } // JAXB heeft een default constructor nodig

    FiliaalModel(Filiaal filiaal, EntityLinks entityLinks) {
        this.filiaal = filiaal;
        super.add(entityLinks.linkToItemResource(
                Filiaal.class, filiaal.getId()));
        super.add(entityLinks.linkFor(Filiaal.class, filiaal.getId())
                .slash("werknemers").withRel("werknemers"));
    }
}