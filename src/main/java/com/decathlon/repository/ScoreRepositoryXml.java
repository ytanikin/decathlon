package com.decathlon.repository;

import com.decathlon.model.AthletePoints;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.nio.file.Paths;

public class ScoreRepositoryXml implements ScoreRepository {

    private final String path;

    public ScoreRepositoryXml(String path) {
        this.path = path;
    }

    @Override
    public void save(AthletePoints athletePoints) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(athletePoints.getClass());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(athletePoints, Paths.get(path).toFile());
        } catch (JAXBException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
