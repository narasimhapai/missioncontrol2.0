artifactory("artifactory-name") {
  proxies {
    proxy('source-item-name') {
      host 'master'
      port 1234
      username 'admin'
      password 'password'
      defaultProxy false
      ntHost 'host'
      ntDomain 'domain'
      redirectedToHosts 'host1, host2, host3'
    }
  }

  propertySets {
    propertySet('source-item-name') {
      singleSelect('property_name') {
        defaultValue "value_1"
        value "value_2"
        value "value_3"
      }
      multiSelect('property_name_multi') {
        defaultValue "value_1"
        defaultValue "value_2"
        value "value_3"
        value "value_4"
      }
      anyValue('another_property_name') {
        defaultValue "value_1"
        value "value_2"
      }

    }
  }

  repoLayouts {
    repoLayout('source-item-name') {
      folderIntegrationRevisionRegExp "SNAPSHOT"
      fileIntegrationRevisionRegExp "SNAPSHOT|(?:(?:[0-9]{8}.[0-9]{6})-(?:[0-9]+))"
      distinctiveDescriptorPathPattern true
      artifactPathPattern "[orgPath]/[module]/[baseRev](-[folderItegRev])/[module]-[baseRev](-[fileItegRev])(-[classifier]).[ext]"
      descriptorPathPattern "[orgPath]/[module]/[baseRev](-[folderItegRev])/[module]-[baseRev](-[fileItegRev])(-[classifier]).pom"
    }
  }

  ldap {
    settings('source-item-name') {
      url 'ldap://myserver:myport/DC=sampledomain,DC=com'
      userDnPattern 'uid={0},ou=People'
      emailAttribute 'mail'
      enabled true
      autoCreateUser true
      search {
        filter '(uid={0})'
        base 'OU=dev,DC=sampledomain,DC=com'
        searchSubTree true
        managerDn 'CN=admin,OU=ops,DC=sampledomain,DC=com'
        managerPassword 'password'
      }
    }

    groupSettings('source-item-name') {
      settings and
      staticMapping {
        groupMemberAttribute 'uniqueMember'
        groupNameAttribute 'cn'
        descriptionAttribute 'description'
        filter '(objectClass=groupOfNames)'
        searchBase ''
        searchSubTree true
      }
    }
  }

  sso {
    http {
      httpSsoProxied false
      remoteUserRequestVariable 'REMOTE_USER'
      autoUserCreation false
      allowUserToAccessProfile false
    }
  }

  security {
    users {
      user('name') {
        email 'login_1@jfrog.com'
        password 'passwd_1'
        admin false
        profileUpdatable false
        internalPasswordDisabled false
        groups(['groupA', 'groupB'])
      }
    }
    groups {
      group('name') {
        description 'desc_1'
        autoJoin false
      }
    }
    permissions {
      permission('name') {
        includesPattern '**'
        excludesPattern ''
        anyLocal false
        anyRemote false
        anyDistribution false
        repositories(["local-rep1", "local-rep2"])
      }
    }
  }

  localRepository('local-repository-key') {
    description "Public description"
    notes "Some internal notes"
    includesPattern "**/*" // default
    excludesPattern "" // default
    repoLayoutRef "maven-2-default"
    propertySets // (["ps1", "ps2"])
    archiveBrowsingEnabled false
    blackedOut false // default
    packageType "generic"
  }

  remoteRepository('remote-repository-key') {
    description "Public description"
    notes "Some internal notes"
    includesPattern "**/*" // default
    excludesPattern "" // default
    repoLayoutRef "maven-2-default"
    propertySets // (["ps1", "ps2"])
    archiveBrowsingEnabled false
    blackedOut false // default
    url "http://host:port/some-repo"
    username "remote-repo-user"
    password "pass"
    proxy "proxy-ref"
  }

  virtualRepository('virtual-repository-key') {
    description "Public description"
    notes "Some internal notes"
    includesPattern "**/*" // default
    excludesPattern "" // default
    repoLayoutRef "maven-2-default"
    repositories(["local-rep1", "local-rep2"])
  }
}

xray("xray-name") {
  watch('watch') {
    binaryManagerId 'binaryManagerId'
    targetType 'repository'
    description 'description'
    active true
    postActions {
      emails(['email1@email.com', 'email2@email.com'])
      slacks 'slacks'
      webhooks(['webhook1', 'webhook2'])
      failBuild true
    }
    filters {
      filter {
        type 'license_black'
        value 'value1'
      }
      filter {
        type 'regex'
        value 'value1'
      }
    }
    repoType 'repoType'
    severity 'severity'
    system true
    targetName 'targetName'
    temp true
  }

  binaryManager('artifactory-name') {
    url 'http://artifactory.com/artifactory'
    login 'login'
    password 'password'
  }
}
