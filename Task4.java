import java.util.*;

public class CPUScheduler {

    // Job class created with all of its attributes constructed
    static class Job {
        int id;
        int priority;
        int duration;
        int remainingTime;
       
        public Job(int id, int priority, int duration) {
            this.id = id;
            this.priority = priority;
            this.duration = duration;
            this.remainingTime = duration;
        }
    }
   
    public static void main(String[] args) {
        // Sample input 
        List<Job> jobList = new ArrayList<>();
        jobList.add(new Job(1, 0, 10));
        jobList.add(new Job(2, 0, 5));
       
        // Initialization of variables
        int currentTime = 0;
        int numJobs = jobList.size();
        int completedJobs = 0;
        boolean[] jobCompleted = new boolean[numJobs];
        Job currentJob = null;
       
        // Creating cases for different schedualing policies
        while (completedJobs < numJobs) {
            // Find job to schedule according to chosen policy
            switch (args[0]) {
                case "FCFS":
                    for (Job job : jobList) {
                        if (!jobCompleted[job.id - 1]) {
                            currentJob = job;
                            break;
                        }
                    }
                    break;
                case "HP":
                    int highestPriority = Integer.MAX_VALUE;
                    for (Job job : jobList) {
                        if (!jobCompleted[job.id - 1] && job.priority < highestPriority) {
                            highestPriority = job.priority;
                            currentJob = job;
                        }
                    }
                    break;
                case "SRTF":
                    int shortestTime = Integer.MAX_VALUE;
                    for (Job job : jobList) {
                        if (!jobCompleted[job.id - 1] && job.remainingTime < shortestTime) {
                            shortestTime = job.remainingTime;
                            currentJob = job;
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid scheduling policy chosen.");
                    System.exit(1);
            }
           
           
            System.out.println("Time slice " + currentTime + ": Job " + currentJob.id);
            currentJob.remainingTime--;
            currentTime++;
           
         
            if (currentJob.remainingTime == 0) {
                jobCompleted[currentJob.id - 1] = true;
                completedJobs++;
            }
        }
    }
}
