// Copyright 2011 The Apache Software Foundation
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package org.example.app3.services;

import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.Contribute;
import org.apache.tapestry5.ioc.annotations.SubModule;
import org.apache.tapestry5.ioc.services.ApplicationDefaults;
import org.apache.tapestry5.ioc.services.SymbolProvider;
import org.apache.tapestry5.jpa.JpaModule;
import org.apache.tapestry5.jpa.JpaSymbols;

@SubModule(JpaModule.class)
public class AppModule
{
    @Contribute(SymbolProvider.class)
    @ApplicationDefaults
    public static void provideFactoryDefaults(
            final MappedConfiguration<String, String> configuration)
    {
        configuration.add(JpaSymbols.PERSISTENCE_DESCRIPTOR, "/jndi-datasource-persistence-unit.xml");
    }
}
