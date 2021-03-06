/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.notifications.uad.exporter.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;
import com.liferay.notifications.uad.test.UserNotificationEventUADTestHelper;
import com.liferay.portal.kernel.model.UserNotificationEvent;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.rule.DeleteAfterTestRun;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.user.associated.data.exporter.UADExporter;
import com.liferay.user.associated.data.test.util.BaseUADExporterTestCase;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.runner.RunWith;

/**
 * @author Brian Wing Shun Chan
 */
@RunWith(Arquillian.class)
public class UserNotificationEventUADExporterTest
	extends BaseUADExporterTestCase<UserNotificationEvent> {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@After
	public void tearDown() throws Exception {
		_userNotificationEventUADTestHelper.cleanUpDependencies(
			_userNotificationEvents);
	}

	@Override
	protected UserNotificationEvent addBaseModel(long userId) throws Exception {
		UserNotificationEvent userNotificationEvent =
			_userNotificationEventUADTestHelper.addUserNotificationEvent(
				userId);

		_userNotificationEvents.add(userNotificationEvent);

		return userNotificationEvent;
	}

	@Override
	protected String getPrimaryKeyName() {
		return "userNotificationEventId";
	}

	@Override
	protected UADExporter getUADExporter() {
		return _uadExporter;
	}

	@Inject(filter = "component.name=*.UserNotificationEventUADExporter")
	private UADExporter _uadExporter;

	@DeleteAfterTestRun
	private final List<UserNotificationEvent> _userNotificationEvents =
		new ArrayList<>();

	@Inject
	private UserNotificationEventUADTestHelper
		_userNotificationEventUADTestHelper;

}