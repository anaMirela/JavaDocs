package ro.teamnet.zth.appl.domain;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

/**
 * Created by Mi on 4/29/2015.
 */
@Table(name="jobs")
public class Jobs {

    @Id(name="job_id")
    private int jobId;

    @Column(name="job_title")
    private String jobTitle;

    @Column(name="min_salary")
    private int minSalary;

    @Column(name="max_salary")
    private int maxSalary;

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(int minSalary) {
        this.minSalary = minSalary;
    }

    public int getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(int maxSalary) {
        this.maxSalary = maxSalary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jobs jobs = (Jobs) o;

        if (jobId != jobs.jobId) return false;
        if (minSalary != jobs.minSalary) return false;
        if (maxSalary != jobs.maxSalary) return false;
        return !(jobTitle != null ? !jobTitle.equals(jobs.jobTitle) : jobs.jobTitle != null);

    }

    @Override
    public int hashCode() {
        int result = jobId;
        result = 31 * result + (jobTitle != null ? jobTitle.hashCode() : 0);
        result = 31 * result + minSalary;
        result = 31 * result + maxSalary;
        return result;
    }

    @Override
    public String toString() {
        return "Jobs{" +
                "jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                ", minSalary=" + minSalary +
                ", maxSalary=" + maxSalary +
                '}';
    }
}
