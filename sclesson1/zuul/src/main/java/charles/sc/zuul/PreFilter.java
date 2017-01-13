package charles.sc.zuul;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Charles on 2016/8/26.
 */
public class PreFilter extends ZuulFilter {
    private ConcurrentHashMap<String, String> tokenUidCache = new ConcurrentHashMap<>();

    public PreFilter() {
        tokenUidCache.put("abc123", "100001");
        tokenUidCache.put("def456", "100002");
    }

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String uri = request.getRequestURI();

        if (Application.publicPattern.matcher(uri).find()) {
            // 直接放行
            return null;
        } else if (Application.protectedPattern.matcher(uri).find() || Application.privatePattern.matcher(uri).find()) {
            // 验证token
            String authorization = request.getHeader("authorization");
            if (!StringUtils.isEmpty(authorization)) {
                String uid = tokenUidCache.get(authorization);
                if (!StringUtils.isEmpty(uid)) {
                    // 将user加入头
                    ctx.addZuulRequestHeader("uid", uid);
                    ctx.setSendZuulResponse(true);
                    return null;
                }
            }
            // 没有授权或授权过期
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            return null;
        } else {
            // 非法访问
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(HttpStatus.FORBIDDEN.value());
            return null;
        }
    }
}
