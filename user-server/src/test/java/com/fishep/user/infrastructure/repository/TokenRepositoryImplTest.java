package com.fishep.user.infrastructure.repository;

import com.fishep.user.domain.repository.TokenRepository;
import com.fishep.user.infrastructure.assembler.TokenDOAssembler;
import com.fishep.user.infrastructure.assembler.UserDOAssembler;
import com.fishep.user.infrastructure.dao.TokenDao;
import org.mockito.InjectMocks;
import org.mockito.Spy;

/**
 * 对于接口类使用集成测试，启动应用，由应用进行依赖管理，注入管理，涉及多个实现类
 * 对于实现类使用单元测试，不启动应用，仅对单个类进行测试
 *
 * @TODO 单元测试
 */
class TokenRepositoryImplTest {

    @Spy
    private UserDOAssembler userDOAssembler;

    @Spy
    private TokenDOAssembler tokenDOAssembler;

    @Spy
    private TokenDao codeTokenDao;

    @Spy
    private TokenDao passwordTokenDao;

    @InjectMocks
    private TokenRepository tokenRepository = new TokenRepositoryImpl();

}