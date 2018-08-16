package com.decathlon;

import com.decathlon.repository.ResultRepository;
import com.decathlon.repository.ResultRepositoryCsv;
import com.decathlon.repository.ScoreRepository;
import com.decathlon.repository.ScoreRepositoryXml;
import com.decathlon.service.PointCalculationService;
import com.decathlon.service.PointCalculationServiceImpl;

public class MainClass {

    public static void main(String[] args) {
        String resultPath = args.length == 2 ? args[0] : "data/results.csv";
        String pathToSaveResults = args.length == 2 ? args[1] : "data/score.xml";

        ResultRepository resultRepository = new ResultRepositoryCsv(resultPath);
        ScoreRepository scoreRepository = new ScoreRepositoryXml(pathToSaveResults);
        PointCalculationService pointCalculationService = new PointCalculationServiceImpl();

        Application application = new Application(resultRepository, scoreRepository, pointCalculationService);
        application.run();
        System.out.println("Results saved in file " + pathToSaveResults);
    }
}
