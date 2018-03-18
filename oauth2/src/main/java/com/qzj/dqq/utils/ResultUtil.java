package com.qzj.dqq.utils;

import com.qzj.dqq.domain.Result;
import com.qzj.dqq.enums.ResultEnum;

/**
 * Created by qzj on 2018/2/25
 */
public class ResultUtil {
        public static Result success(ResultEnum resultEnum, Object object) {
            Result result = new Result();
            result.setCode(resultEnum.getCode());
            result.setMessage(resultEnum.getMsg());
            result.setData(object);
            return result;
        }

        public static Result error(Integer code, String msg) {
            Result result = new Result();
            result.setCode(code);
            result.setMessage(msg);
            return result;
        }
}
