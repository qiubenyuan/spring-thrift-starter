package info.developerblog.spring.thrift.client.pool;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.thrift.TServiceClient;

/**
 * Created by aleksandr on 11.10.15.
 */
public class ThriftClientKey {
    private Class<? extends TServiceClient> clazz;
    private String serviceName;
    private String path;

    public void setClazz(Class<? extends TServiceClient> clazz) {
        this.clazz = clazz;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServiceName() {
        if (StringUtils.isEmpty(serviceName)) {
            return WordUtils.uncapitalize(clazz.getEnclosingClass().getSimpleName());
        }
        return serviceName;
    }

    public Class<? extends TServiceClient> getClazz() {
        return clazz;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ThriftClientKey that = (ThriftClientKey) o;

        if (clazz != null ? !clazz.equals(that.clazz) : that.clazz != null) return false;
        if (serviceName != null ? !serviceName.equals(that.serviceName) : that.serviceName != null) return false;
        return path != null ? path.equals(that.path) : that.path == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (clazz != null ? clazz.hashCode() : 0);
        result = 31 * result + (serviceName != null ? serviceName.hashCode() : 0);
        result = 31 * result + (path != null ? path.hashCode() : 0);
        return result;
    }
}
