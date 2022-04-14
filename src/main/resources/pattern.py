# encoding=utf-8

import sys
from pathlib import Path
base_path = Path(__file__).parents[3]
sys.path.append(str(base_path))
import pytest
from httprunner import HttpRunner, Config, Step, RunRequest, Parameters
from medicalrecord.myhttprunner.myTestCase import MyStep as Step


class TestGetShareInfoByMrId(HttpRunner):
    @pytest.mark.parametrize("param", Parameters({
    	${csv}
        }))
    def test_start(self, param):
        super().test_start(param)

    config = Config("${testName}").verify(False)
    """
    description:${testName}
    caseId:
    author:jiangchunliang
    terminal:${terminal}
    """
    teststeps = [
        Step(
            RunRequest("${testName}")
            .with_variables(
                **{
                    "data": {
                        ${param},
                        ${token}
                    }
                }
            ).post("${url}")
            .extract()
            .validate()
        ).uni_deal(),
    ]
