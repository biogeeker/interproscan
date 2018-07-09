package uk.ac.ebi.interpro.scan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.HashSet;
import java.util.Set;

/**
 * MobiDB filtered match.
 *
 * @author Gift Nuka
 * @version $Id$
 * @since 1.0-SNAPSHOT
 */
@Entity
@Table(name = "mobidb_match")
@XmlType(name = "MobiDBMatchType")
public class MobiDBMatch extends Match<MobiDBMatch.MobiDBLocation> {



    protected MobiDBMatch() {
    }

    public MobiDBMatch(Signature signature, String signatureModels, Set<MobiDBLocation> locations) {
        super(signature, signatureModels, locations);
    }

    public Object clone() throws CloneNotSupportedException {
        final Set<MobiDBLocation> clonedLocations = new HashSet<MobiDBLocation>(this.getLocations().size());
        for (MobiDBLocation location : this.getLocations()) {
            clonedLocations.add((MobiDBLocation) location.clone());
        }
        return new MobiDBMatch(this.getSignature(), this.getSignatureModels(),  clonedLocations);
    }


    /**
     * Location of disordered region on protein sequence
     */
    @Entity
    @Table(name = "mobidb_location")
    @XmlType(name = "MobiDBLocationType", namespace = "http://www.ebi.ac.uk/interpro/resources/schemas/interproscan5")
    public static class MobiDBLocation extends Location {

        String sequenceFeature;

        /**
         * Protected no-arg constructor required by JPA - DO NOT USE DIRECTLY.
         */
        protected MobiDBLocation() {
        }

        public MobiDBLocation(int start, int end) {
            super(start, end);
        }

        public MobiDBLocation(int start, int end, String sequenceFeature) {
            super(start, end);
            setSequenceFeature(sequenceFeature);
        }

        // TODO Enable this field when data can be populated
        //@XmlAttribute(name = "sequence-feature", required = true)
        @XmlTransient
        @JsonIgnore
        public String getSequenceFeature() {
            return sequenceFeature;
        }

        public void setSequenceFeature(String sequenceFeature) {
            this.sequenceFeature = sequenceFeature;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof MobiDBLocation))
                return false;
            final MobiDBLocation f = (MobiDBLocation) o;
            return new EqualsBuilder()
                    .appendSuper(super.equals(o))
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(43, 79)
                    .appendSuper(super.hashCode())
                    .toHashCode();
        }

        public Object clone() throws CloneNotSupportedException {
            return new MobiDBLocation(this.getStart(), this.getEnd());
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MobiDBMatch that = (MobiDBMatch) o;

        return true;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(431, 791)
                .appendSuper(super.hashCode())
                .toHashCode();
    }
}
