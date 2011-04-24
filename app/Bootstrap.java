import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

/**
 * @author Lukasz Piliszczuk <lukasz.pili AT gmail.com>
 */
@OnApplicationStart
public class Bootstrap extends Job {

    @Override
    public void doJob() throws Exception {

        //Fixtures.deleteAllModels();
        //Fixtures.loadModels("data.yml");

        if(User.find("byEmail", "tatianay@gmail.com").first() == null) {
            Fixtures.loadModels("data.yml");
        }
    }
}
